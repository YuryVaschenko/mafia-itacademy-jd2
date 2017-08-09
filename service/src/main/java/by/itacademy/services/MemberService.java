package by.itacademy.services;

import by.itacademy.dto.RegisterNewAuthorityInfoSample;
import by.itacademy.dto.RegisterNewCaporegimeInfoSample;
import by.itacademy.entity.Member;

/**
 * Created by Yury V. on 18.07.17.
 */

public interface MemberService {

    Member findMemberByLogin(String login);

    Long saveNewAuthority(Long clanId, RegisterNewAuthorityInfoSample authorityInfoSample);

    Long saveNewCaporegime(Long clanId, RegisterNewCaporegimeInfoSample caporegimeInfoSample);

}
