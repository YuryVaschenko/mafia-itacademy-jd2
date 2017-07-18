package by.itacademy.services;

import by.itacademy.entity.Member;

/**
 * Created by Yury V. on 18.07.17.
 */

public interface MemberService {

    Member findMemberByLogin(String login);

}
