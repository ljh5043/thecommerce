package thecommerce.toy.global.util.random;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomStringUtil {



    // 랜덤한 문자열을 생성하는 메서드
    public String generateRandomString(int length) {
        // 생성할 문자열에 사용할 문자들
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        Random random = new Random();
        // 랜덤한 문자열을 저장할 StringBuilder 객체 생성
        StringBuilder sb = new StringBuilder(length);

        // 지정된 길이만큼 랜덤한 문자열 생성
        for (int i = 0; i < length; i++) {
            // characters 문자열에서 랜덤한 인덱스 선택
            int randomIndex = random.nextInt(characters.length());

            // 선택한 인덱스에 해당하는 문자를 StringBuilder에 추가
            sb.append(characters.charAt(randomIndex));
        }

        // 생성된 랜덤 문자열 반환
        return sb.toString();
    }

    public static String generateRandomNumber() {
        Random random = new Random();
        long randomNumber = (long) random.nextInt(900000000) + 100000000;
        return Long.toString(randomNumber);
    }
}
