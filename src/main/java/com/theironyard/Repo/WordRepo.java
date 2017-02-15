package com.theironyard.Repo;

import com.theironyard.entitiies.Word;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by erikjakubowski on 1/31/17.
 */
public interface WordRepo extends CrudRepository<Word, Integer> {

    Word findFirstByWord(String word);


}
