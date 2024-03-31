package com.jrn.journalapp.scheduler;

import com.jrn.journalapp.Enum.Sentiment;
import com.jrn.journalapp.cache.AppCache;
import com.jrn.journalapp.entity.User;
import com.jrn.journalapp.entity.journal_entries;
import com.jrn.journalapp.repo.UserRepoImpl;
import com.jrn.journalapp.service.EmailService;
import com.jrn.journalapp.service.Journalentryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Component
public class UserScheduler {


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepository;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepository.getUserForSA();
        for (User user : users) {
            List<journal_entries> journalEntries = user.getJournal_entriesList();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null) {
                emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days ", mostFrequentSentiment.toString());
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }
}
