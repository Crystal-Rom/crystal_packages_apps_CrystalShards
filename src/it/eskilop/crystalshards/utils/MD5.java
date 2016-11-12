/*
 *    Copyright 2016 The Crystal Rom Project
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package it.eskilop.crystalshards.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by: Eskilop@Shiva
 * on: 12/11/16.
 */

public class MD5
  {
    static MessageDigest m;
    private static String tohash = "";

    public MD5(String input)
      {
        tohash = input;
      }

    public String getMD5()
      {
        try
          {
            m = MessageDigest.getInstance("MD5");
          }
        catch (Exception e)
          {
            System.out.print("No MD5 for you. Sorry");
          }
        m.reset();
        m.update(tohash.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        while(hashtext.length() < 32 )
          {
            hashtext = "0"+hashtext;
          }
        return hashtext;
      }
  }
