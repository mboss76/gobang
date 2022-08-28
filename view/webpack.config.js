const path = require('path')
const {VueElement} = require("vue");
const {VueLoaderPlugin} = require("vue-loader");
module.exports = {
    module: {
        rules: [
            { test: /\.vue$/, use: 'vue-loader' },
            {
                test: /\.tsx?$/,
                loader: 'ts-loader',
                exclude: /node_modules/,
                options: {
                    appendTsSuffixTo: [/\.vue$/],
                }
            }
        ]
    },
    entry:path.join(__dirname,'./src/main.js'),
    output:{
        path:path.join(__dirname,'dist'),
        filename:'bundle.js'
    },
    plugins: [
        new VueLoaderPlugin()
    ]

}
