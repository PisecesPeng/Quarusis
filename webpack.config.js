const path = require('path');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const UglifyJSPlugin = require('uglifyjs-webpack-plugin');

module.exports = {
    entry: {
        node_app: './node_src/in_chat.js',
        node_pulic: './node_res/js/node_public.js',
        quarusis_pulic: './src/main/webapp/res/quarusis/quarusis_public.js',
        quarusis_homepage: './src/main/webapp/res/quarusis/home_center/home_page.js',
        quarusis_usersetting: './src/main/webapp/res/quarusis/home_center/user_setting.js',
        quarusis_indexpage: './src/main/webapp/res/quarusis/index_center/index_page.js',
        quarusis_topicpage: './src/main/webapp/res/quarusis/index_center/topic_page.js',
        quarusis_page: './src/main/webapp/res/quarusis/page_center/page.js',
    },
    devtool: 'inline-source-map',
    plugins: [
        new CleanWebpackPlugin(['dist']),
        new UglifyJSPlugin()
    ],
    output:{
        filename:'[name].bundle.js',
        path:path.resolve(__dirname,'dist'),
        publicPath: '/'
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
            },
            {
                test: /\.(png|svg|jpg|gif)$/,
                use: [
                    'file-loader'
                ]
            }
        ]
    }
};

