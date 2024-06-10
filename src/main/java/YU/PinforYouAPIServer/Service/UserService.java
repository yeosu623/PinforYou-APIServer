package YU.PinforYouAPIServer.Service;

import YU.PinforYouAPIServer.Entity.ChallengeEntity;
import YU.PinforYouAPIServer.Entity.PointShopEntity;
import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Other.StringAnd1DArrayConverter;
import YU.PinforYouAPIServer.Other.StringAnd2DArrayConverter;
import YU.PinforYouAPIServer.Repository.ChallengeRepository;
import YU.PinforYouAPIServer.Repository.PointShopRepository;
import YU.PinforYouAPIServer.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ChallengeRepository challengeRepository;
    @Autowired
    PointShopRepository pointShopRepository;

    StringAnd1DArrayConverter stringAnd1DArrayConverter;
    StringAnd2DArrayConverter stringAnd2DArrayConverter;

    public void change_id(Integer user_id, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("id", value);

        userRepository.put_basic_info(user_id, map);
    }

    public void change_pw(Integer user_id, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("pw", value);

        userRepository.put_basic_info(user_id, map);
    }

    public void change_tel(Integer user_id, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("tel", value);

        userRepository.put_basic_info(user_id, map);
    }

    public void change_sex(Integer user_id, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("sex", value);

        userRepository.put_basic_info(user_id, map);
    }

    public void change_age(Integer user_id, Integer value) {
        Map<String, String> map = new HashMap<>();
        map.put("age", value.toString());

        userRepository.put_basic_info(user_id, map);
    }

    public void change_interest(Integer user_id, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("interest", value);

        userRepository.put_basic_info(user_id, map);
    }

    public void change_point(Integer user_id, Integer value) {
        Map<String, String> map = new HashMap<>();
        map.put("point", value.toString());

        userRepository.put_basic_info(user_id, map);
    }

    public void add_friend(Integer user_id, Integer friend_id) {
        UserEntity user = userRepository.get(user_id);

        ArrayList<String> friends = stringAnd1DArrayConverter.stoa(user.getFriend());
        for(int i = 0; i < friends.size(); i++)
            if(friends.get(i).equals(friend_id.toString()))
                return;

        friends.add(friend_id.toString());
        user.setFriend(stringAnd1DArrayConverter.atos(friends));
    }

    public void delete_friend(Integer user_id, Integer friend_id) {
        UserEntity user = userRepository.get(user_id);

        ArrayList<String> friends = stringAnd1DArrayConverter.stoa(user.getFriend());
        for(int i = 0; i < friends.size(); i++)
            if(friends.get(i).equals(friend_id.toString()))
                friends.remove(i);

        user.setFriend(stringAnd1DArrayConverter.atos(friends));
    }

    public void start_challenge(Integer user_id, Integer challenge_id) {
        UserEntity user = userRepository.get(user_id);
        ChallengeEntity challenge = challengeRepository.get(challenge_id);

        ArrayList<String> challenge_new = new ArrayList<>();
        challenge_new.add(challenge_id.toString());
        challenge_new.add("1");
        challenge_new.add(challenge.getGoal().toString());
        challenge_new.add("false");

        ArrayList<ArrayList<String>> challenge_array = stringAnd2DArrayConverter.stoa(user.getChallenge_progress());
        challenge_array.add(challenge_new);
    }

    public void increase_challenge_progress(Integer user_id, Integer challenge_id) {
        UserEntity user = userRepository.get(user_id);
        ArrayList<ArrayList<String>> challenge_array = stringAnd2DArrayConverter.stoa(user.getChallenge_progress());

        for(int i = 0; i < challenge_array.size(); i++) {
            if(challenge_array.get(i).get(0).equals(challenge_id.toString())) {
                Integer progress = Integer.parseInt(challenge_array.get(i).get(1));
                progress++;
                challenge_array.get(i).set(1, progress.toString());

                Integer goal = Integer.parseInt(challenge_array.get(i).get(2));
                if(progress.equals(goal))
                    challenge_array.get(i).set(3, "true");

                return;
            }
        }
    }

    public void add_item(Integer user_id, Integer item_id) {
        UserEntity user = userRepository.get(user_id);
        Random random = new Random();
        Long barcode = random.nextLong(8999_9999_9999L) + 1000_0000_0000L;

        ArrayList<String> item_new = new ArrayList<>();
        item_new.add(item_id.toString());
        item_new.add(barcode.toString());

        ArrayList<ArrayList<String>> items_array = stringAnd2DArrayConverter.stoa(user.getItem_list());
        items_array.add(item_new);
    }

    public void delete_item(Integer user_id, Integer item_id) {
        UserEntity user = userRepository.get(user_id);
        ArrayList<ArrayList<String>> items_array = stringAnd2DArrayConverter.stoa(user.getItem_list());

        for(int i = 0; i < items_array.size(); i++)
            if(items_array.get(i).get(0).equals(item_id.toString()))
                items_array.remove(i);

        user.setItem_list(stringAnd2DArrayConverter.atos(items_array));
    }

    public void add_friend_request(Integer user_id, Integer friend_id) {
        UserEntity user = userRepository.get(user_id);

        ArrayList<String> friend_requests = stringAnd1DArrayConverter.stoa(user.getFriend_request());
        for(int i = 0; i < friend_requests.size(); i++)
            if(friend_requests.get(i).equals(friend_id.toString()))
                return;

        friend_requests.add(friend_id.toString());
        user.setFriend_request(stringAnd1DArrayConverter.atos(friend_requests));
    }

    public void accept_friend_request(Integer user_id, Integer friend_id) {
        UserEntity user = userRepository.get(user_id);

        ArrayList<String> friend_requests = stringAnd1DArrayConverter.stoa(user.getFriend_request());
        for(int i = 0; i < friend_requests.size(); i++)
            if(friend_requests.get(i).equals(friend_id.toString())) {
                friend_requests.remove(i);
                add_friend(user_id, friend_id);
            }

        user.setFriend_request(stringAnd1DArrayConverter.atos(friend_requests));
    }

    public void delete_friend_request(Integer user_id, Integer friend_id) {
        UserEntity user = userRepository.get(user_id);

        ArrayList<String> friend_requests = stringAnd1DArrayConverter.stoa(user.getFriend_request());
        for(int i = 0; i < friend_requests.size(); i++)
            if(friend_requests.get(i).equals(friend_id.toString()))
                friend_requests.remove(i);

        user.setFriend_request(stringAnd1DArrayConverter.atos(friend_requests));
    }

    public void add_group_request(Integer user_id, Integer group_id) {
        UserEntity user = userRepository.get(user_id);

        ArrayList<String> group_requests = stringAnd1DArrayConverter.stoa(user.getGroup_request());
        for(int i = 0; i < group_requests.size(); i++)
            if(group_requests.get(i).equals(group_id.toString()))
                return;

        group_requests.add(group_id.toString());
        user.setGroup_request(stringAnd1DArrayConverter.atos(group_requests));
    }

    public void accept_group_request(Integer user_id, Integer group_id) {
        UserEntity user = userRepository.get(user_id);

        ArrayList<String> group_requests = stringAnd1DArrayConverter.stoa(user.getGroup_request());
        for(int i = 0; i < group_requests.size(); i++)
            if(group_requests.get(i).equals(group_id.toString())) {
                group_requests.remove(i);
                add_friend(user_id, group_id);
            }

        user.setFriend_request(stringAnd1DArrayConverter.atos(group_requests));
    }

    public void delete_group_request(Integer user_id, Integer group_id) {
        UserEntity user = userRepository.get(user_id);

        ArrayList<String> group_requests = stringAnd1DArrayConverter.stoa(user.getGroup_request());
        for(int i = 0; i < group_requests.size(); i++)
            if(group_requests.get(i).equals(group_id.toString()))
                group_requests.remove(i);

        user.setFriend_request(stringAnd1DArrayConverter.atos(group_requests));
    }
}
