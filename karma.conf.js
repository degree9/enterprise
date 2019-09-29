module.exports = function (config) {
    config.set({
        browsers: ["FirefoxHeadless"],
        // The directory where the output file lives
        basePath: "test",
        // The file itself
        files: ["ci.js"],
        frameworks: ["cljs-test"],
        plugins: ["karma-cljs-test", "karma-firefox-launcher"],
        colors: true,
        logLevel: config.LOG_INFO,
        client: {
            args: ["shadow.test.karma.init"],
            singleRun: true
        }
    })
};
