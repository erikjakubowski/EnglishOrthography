package com.theironyard.Repo;

import com.theironyard.entitiies.Letter;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by erikjakubowski on 2/8/17.
 */
public interface LetterRepo extends CrudRepository<Letter, Integer> {
    Letter findFirstByLetter(String letter);


}
