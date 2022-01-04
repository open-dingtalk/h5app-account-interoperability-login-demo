"use strict";
const path = require("path");
const { resourceUsage } = require("process");

function resolve(dir) {
    return path.join(__dirname, dir);
}

const port = process.env.port || process.env.npm_config_port || 8080; // dev port

module.exports = {
    publicPath: "/",
    outputDir: "dist",
    assetsDir: "static",
    productionSourceMap: false,
    devServer: {
        host: "localhost",
        port: port,
        open: true,
        overlay: {
            warnings: true,
            errors: true,
        },
        proxy: {
            '/api': {
                target: 'http://localhost:9999',
                secure: false,
                // 设置跨域
                changeOrigin: true,
                // pathRewrite: {
                //     '^/api': ''
                // }
            },
        },
    },
    configureWebpack: {
        // provide the app's title in webpack's name field, so that
        // it can be accessed in index.html to inject the correct title.
        name: "login",
        resolve: {
            alias: {
                "@": resolve("src"),
            },
        },
    },
};
