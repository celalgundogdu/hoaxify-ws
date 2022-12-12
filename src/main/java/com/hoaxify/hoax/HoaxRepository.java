package com.hoaxify.hoax;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HoaxRepository extends JpaRepository<Hoax, Long> {

}
