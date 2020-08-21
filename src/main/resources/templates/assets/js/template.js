class VueFactory {

    constructor(property = {}) {
        this.vm = new Vue(Object.assign({ el: '#app' }, property));
    }

    static create(property = {}) {
        return new VueFactory(property)
    }

}
