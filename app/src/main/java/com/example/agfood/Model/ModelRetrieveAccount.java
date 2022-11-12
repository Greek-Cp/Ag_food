package com.example.agfood.Model;

import com.google.gson.annotations.SerializedName;

public class ModelRetrieveAccount {
        private String username;
        private String email;
        @SerializedName("password_akun")
        private String password;
        private String kedudukan;
        @SerializedName("detail_akun")
        private ModelDetailAccount modelDetailAccount;
        public String getUsername() {
            return username;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public String getKedudukan() {
            return kedudukan;
        }

        public void setKedudukan(String kedudukan) {
            this.kedudukan = kedudukan;
        }

        public ModelDetailAccount getModelDetailAccount() {
            return modelDetailAccount;
        }

        public void setModelDetailAccount(ModelDetailAccount modelDetailAccount) {
            this.modelDetailAccount = modelDetailAccount;
        }

        public ModelRetrieveAccount(String username, String password, String kedudukan , String email) {
            this.username = username;
            this.password = password;
            this.kedudukan = kedudukan;
            this.email = email;
        }
    }

