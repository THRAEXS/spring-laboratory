const Factory = {};

(() => {
    'use strict';

    class VueFactory {

        constructor(property = {}) {
            if (!Vue) throw new Error('Vue is not defined');

            this.vue = new Vue(Object.assign({ el: '#app' }, property));
        }

        static build(property = {}) {
            return new VueFactory(property).vue;
        }

    }

    class AxiosFactory {

        constructor(config = {}) {
            if (!axios) throw new Error('axios is not defined');

            this.axios = axios.create(Object.assign({ timeout: 8000 }, config));
            this.axios.interceptors.response.use(
                response => response.data,
                error => {
                    console.error('axios request error.', error);
                    return Promise.reject(error);
                }
            );
        }

        static build(config = {}) {
            return new AxiosFactory(config).axios;
        }

    }

    Factory.Vue = VueFactory;
    Factory.Axios = AxiosFactory;
    Factory.Request = AxiosFactory.build();
})();
