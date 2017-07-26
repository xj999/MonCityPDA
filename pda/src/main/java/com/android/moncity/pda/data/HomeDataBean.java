package com.android.moncity.pda.data;

import java.io.Serializable;

/**
 * @author Luxj
 * @date create 2017/7/24
 * @description
 */
public class HomeDataBean implements Serializable {
    public String method;
    public String ip;
    public String url;
    public String des;
    public String upload;
    public Author author;

    public class Author implements Serializable {
        private static final long serialVersionUID = 2701611773813762723L;

        public String name;
        public String fullname;
        public String github;
        public String address;
        public String qq;
        public String email;
        public String des;

        @Override
        public String toString() {
            return "Author{\n" +//
                    "\tname='" + name + "\'\n" +//
                    "\tfullname='" + fullname + "\'\n" +//
                    "\tgithub='" + github + "\'\n" +//
                    "\taddress='" + address + "\'\n" +//
                    "\tqq='" + qq + "\'\n" +//
                    "\temail='" + email + "\'\n" +//
                    "\tdes='" + des + "\'\n" +//
                    '}';
        }
    }

}
