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

            const csrf = id => {
                const el = document.getElementById(id);
                return el ? el.getAttribute('content') : null;
            };
            this.axios.interceptors.request.use(config => {
                config.headers[csrf('_csrf_header')] = csrf('_csrf_token');
                return config;
            }, error => {
                console.error('axios request error.', error);
                return Promise.reject(error);
            });

            this.axios.interceptors.response.use(
                response => response.data,
                error => {
                    console.error('axios response error.', error);
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
