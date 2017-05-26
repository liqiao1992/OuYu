package com.yizhan.ouyu.entity;

import java.util.List;

/**
 * Created by lenovo on 2017/5/25.
 */

public class DribbbleFollowing {

    /**
     * id : 26132781
     * created_at : 2017-05-17T07:55:17Z
     * followee : {"id":3460,"name":"Ryan Putnam","username":"RypeArts","html_url":"https://dribbble.com/RypeArts","avatar_url":"https://cdn.dribbble.com/users/3460/avatars/normal/4ed7a7a4737e971e3fa37f0705f5cc51.png?1457564367","bio":"designer, illustrator, potter, and father","location":"San Francisco, CA","links":{"web":"http://ryanputn.am","twitter":"https://twitter.com/RypeArts"},"buckets_count":3,"comments_received_count":10820,"followers_count":68930,"followings_count":550,"likes_count":11231,"likes_received_count":284733,"projects_count":32,"rebounds_received_count":449,"shots_count":688,"teams_count":2,"can_upload_shot":true,"type":"Player","pro":true,"buckets_url":"https://api.dribbble.com/v1/users/3460/buckets","followers_url":"https://api.dribbble.com/v1/users/3460/followers","following_url":"https://api.dribbble.com/v1/users/3460/following","likes_url":"https://api.dribbble.com/v1/users/3460/likes","projects_url":"https://api.dribbble.com/v1/users/3460/projects","shots_url":"https://api.dribbble.com/v1/users/3460/shots","teams_url":"https://api.dribbble.com/v1/users/3460/teams","created_at":"2010-07-16T12:48:16Z","updated_at":"2017-05-25T00:42:27Z"}
     */

    private int id;
    private String created_at;
    private FolloweeBean followee;

    private List<DribbbleShot> shots;

    public List<DribbbleShot> getShots() {
        return shots;
    }

    public void setShots(List<DribbbleShot> shots) {
        this.shots = shots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public FolloweeBean getFollowee() {
        return followee;
    }

    public void setFollowee(FolloweeBean followee) {
        this.followee = followee;
    }

    public static class FolloweeBean {
        /**
         * id : 3460
         * name : Ryan Putnam
         * username : RypeArts
         * html_url : https://dribbble.com/RypeArts
         * avatar_url : https://cdn.dribbble.com/users/3460/avatars/normal/4ed7a7a4737e971e3fa37f0705f5cc51.png?1457564367
         * bio : designer, illustrator, potter, and father
         * location : San Francisco, CA
         * links : {"web":"http://ryanputn.am","twitter":"https://twitter.com/RypeArts"}
         * buckets_count : 3
         * comments_received_count : 10820
         * followers_count : 68930
         * followings_count : 550
         * likes_count : 11231
         * likes_received_count : 284733
         * projects_count : 32
         * rebounds_received_count : 449
         * shots_count : 688
         * teams_count : 2
         * can_upload_shot : true
         * type : Player
         * pro : true
         * buckets_url : https://api.dribbble.com/v1/users/3460/buckets
         * followers_url : https://api.dribbble.com/v1/users/3460/followers
         * following_url : https://api.dribbble.com/v1/users/3460/following
         * likes_url : https://api.dribbble.com/v1/users/3460/likes
         * projects_url : https://api.dribbble.com/v1/users/3460/projects
         * shots_url : https://api.dribbble.com/v1/users/3460/shots
         * teams_url : https://api.dribbble.com/v1/users/3460/teams
         * created_at : 2010-07-16T12:48:16Z
         * updated_at : 2017-05-25T00:42:27Z
         */

        private int id;
        private String name;
        private String username;
        private String html_url;
        private String avatar_url;
        private String bio;
        private String location;
        private LinksBean links;
        private int buckets_count;
        private int comments_received_count;
        private int followers_count;
        private int followings_count;
        private int likes_count;
        private int likes_received_count;
        private int projects_count;
        private int rebounds_received_count;
        private int shots_count;
        private int teams_count;
        private boolean can_upload_shot;
        private String type;
        private boolean pro;
        private String buckets_url;
        private String followers_url;
        private String following_url;
        private String likes_url;
        private String projects_url;
        private String shots_url;
        private String teams_url;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public LinksBean getLinks() {
            return links;
        }

        public void setLinks(LinksBean links) {
            this.links = links;
        }

        public int getBuckets_count() {
            return buckets_count;
        }

        public void setBuckets_count(int buckets_count) {
            this.buckets_count = buckets_count;
        }

        public int getComments_received_count() {
            return comments_received_count;
        }

        public void setComments_received_count(int comments_received_count) {
            this.comments_received_count = comments_received_count;
        }

        public int getFollowers_count() {
            return followers_count;
        }

        public void setFollowers_count(int followers_count) {
            this.followers_count = followers_count;
        }

        public int getFollowings_count() {
            return followings_count;
        }

        public void setFollowings_count(int followings_count) {
            this.followings_count = followings_count;
        }

        public int getLikes_count() {
            return likes_count;
        }

        public void setLikes_count(int likes_count) {
            this.likes_count = likes_count;
        }

        public int getLikes_received_count() {
            return likes_received_count;
        }

        public void setLikes_received_count(int likes_received_count) {
            this.likes_received_count = likes_received_count;
        }

        public int getProjects_count() {
            return projects_count;
        }

        public void setProjects_count(int projects_count) {
            this.projects_count = projects_count;
        }

        public int getRebounds_received_count() {
            return rebounds_received_count;
        }

        public void setRebounds_received_count(int rebounds_received_count) {
            this.rebounds_received_count = rebounds_received_count;
        }

        public int getShots_count() {
            return shots_count;
        }

        public void setShots_count(int shots_count) {
            this.shots_count = shots_count;
        }

        public int getTeams_count() {
            return teams_count;
        }

        public void setTeams_count(int teams_count) {
            this.teams_count = teams_count;
        }

        public boolean isCan_upload_shot() {
            return can_upload_shot;
        }

        public void setCan_upload_shot(boolean can_upload_shot) {
            this.can_upload_shot = can_upload_shot;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isPro() {
            return pro;
        }

        public void setPro(boolean pro) {
            this.pro = pro;
        }

        public String getBuckets_url() {
            return buckets_url;
        }

        public void setBuckets_url(String buckets_url) {
            this.buckets_url = buckets_url;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getLikes_url() {
            return likes_url;
        }

        public void setLikes_url(String likes_url) {
            this.likes_url = likes_url;
        }

        public String getProjects_url() {
            return projects_url;
        }

        public void setProjects_url(String projects_url) {
            this.projects_url = projects_url;
        }

        public String getShots_url() {
            return shots_url;
        }

        public void setShots_url(String shots_url) {
            this.shots_url = shots_url;
        }

        public String getTeams_url() {
            return teams_url;
        }

        public void setTeams_url(String teams_url) {
            this.teams_url = teams_url;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public static class LinksBean {
            /**
             * web : http://ryanputn.am
             * twitter : https://twitter.com/RypeArts
             */

            private String web;
            private String twitter;

            public String getWeb() {
                return web;
            }

            public void setWeb(String web) {
                this.web = web;
            }

            public String getTwitter() {
                return twitter;
            }

            public void setTwitter(String twitter) {
                this.twitter = twitter;
            }
        }
    }
}
