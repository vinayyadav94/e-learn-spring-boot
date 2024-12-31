package com.elearn.app.repositories;

import com.elearn.app.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepo extends JpaRepository<Video, String> {
}
