package com.news.persistence;


import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long>{

    News findByCity(String city);
}
