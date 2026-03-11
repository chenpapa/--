package com.example.drama.config;

import com.example.drama.entity.Drama;
import com.example.drama.entity.DramaCategory;
import com.example.drama.entity.DramaStatus;
import com.example.drama.entity.Episode;
import com.example.drama.repository.DramaRepository;
import com.example.drama.repository.EpisodeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDemoData(DramaRepository dramaRepository, EpisodeRepository episodeRepository) {
        return args -> {
            if (dramaRepository.count() > 0) {
                return;
            }

            Drama drama = new Drama();
            drama.setTitle("重生后我在豪门杀疯了");
            drama.setCategory(DramaCategory.REVENGE);
            drama.setDescription("女主重生逆袭，在豪门商战与情感纠葛中完成复仇。");
            drama.setStatus(DramaStatus.PUBLISHED);
            drama.setTotalPlays(12650L);
            drama = dramaRepository.save(drama);

            Episode ep1 = new Episode();
            ep1.setDrama(drama);
            ep1.setEpisodeNo(1);
            ep1.setTitle("重回订婚夜");
            ep1.setDurationSeconds(110);
            ep1.setPlayCount(6200L);

            Episode ep2 = new Episode();
            ep2.setDrama(drama);
            ep2.setEpisodeNo(2);
            ep2.setTitle("布局开始");
            ep2.setDurationSeconds(125);
            ep2.setPlayCount(6450L);

            episodeRepository.save(ep1);
            episodeRepository.save(ep2);

            drama.setTotalEpisodes(2);
            dramaRepository.save(drama);
        };
    }
}
