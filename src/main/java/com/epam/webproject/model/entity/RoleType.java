package com.epam.webproject.model.entity;

    public enum RoleType {

        ADMIN("admin"),
        USER("user");

        private final String value;

        private RoleType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("RoleType{");
            sb.append("value='").append(value).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

