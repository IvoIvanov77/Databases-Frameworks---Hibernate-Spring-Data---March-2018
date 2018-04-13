package app.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomGenerator {

    private static Random random;

    public static Random getInstance(){
        if(random == null){
            random = new Random();
        }

        return random;
    }

}
