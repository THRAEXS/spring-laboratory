package org.thraex.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2020/08/19 09:24
 */
@ConfigurationProperties("thraex.site")
public class SiteProperties {

    private Mode mode = new Mode();

    private Index index = new Index();

    private Admin admin = new Admin();

    public static class Mode {

        /**
         * Whether to enable admin mode
         */
        private boolean enabled = true;

        public String[] permit() {
            return Stream.of(enabled ? null : "/").filter(Objects::nonNull).toArray(String[]::new);
        }

        public String successUrl() {
            return enabled ? "/" : "/admin";
        }

        public String index() {
            return enabled ? "admin" : "index";
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        @Override
        public String toString() {
            return "Mode{" +
                    "enabled=" + enabled +
                    '}';
        }
    }

    public static class Index {

        /**
         * Site title
         */
        private String title = "THRAEX | Index";

        /**
         * Logo path
         */
        private String logo = "/AdminLTE/dist/img/AdminLTELogo.png";

        /**
         * Index view. The optional values refer to the 'views/index' directory
         */
        private String view = "default";

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getView() {
            return view;
        }

        public void setView(String view) {
            this.view = view;
        }

        @Override
        public String toString() {
            return "Index{" +
                    "title='" + title + '\'' +
                    ", logo='" + logo + '\'' +
                    ", view='" + view + '\'' +
                    '}';
        }

    }

    public static class Admin {

        /**
         * Site title
         */
        private String title = "THRAEX | Admin";

        /**
         * Logo path
         */
        private String logo = "/AdminLTE/dist/img/AdminLTELogo.png";

        /**
         * Banner text behind the logo
         */
        private String brand = "THRAEX";

        /**
         * Admin dashboard view. The optional values refer to the 'views/dashboard' directory
         */
        private String dashboard = "default";

        public String getTitle() {
            return title;
        }

        public Admin setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getLogo() {
            return logo;
        }

        public Admin setLogo(String logo) {
            this.logo = logo;
            return this;
        }

        public String getBrand() {
            return brand;
        }

        public Admin setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public String getDashboard() {
            return dashboard;
        }

        public void setDashboard(String dashboard) {
            this.dashboard = dashboard;
        }

        @Override
        public String toString() {
            return "Admin{" +
                    "title='" + title + '\'' +
                    ", logo='" + logo + '\'' +
                    ", brand='" + brand + '\'' +
                    ", dashboard='" + dashboard + '\'' +
                    '}';
        }

    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "SiteProperties{" +
                "mode=" + mode +
                ", index=" + index +
                ", admin=" + admin +
                '}';
    }

}
