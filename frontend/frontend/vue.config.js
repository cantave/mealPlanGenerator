const { defineConfig } = require("@vue/cli-service");
const path = require("path");
const { config } = require("process");

module.exports = defineConfig({
  transpileDependencies: true,
  configureWebpack: {
    resolve: {
      alias: {
        "@": path.resolve(__dirname, "src"),
        "@services": path.resolve(__dirname, "src/services"),
      },
    },
  },
  chainWebpack: (config) => {
    config.plugin("define").tap((definitions) => {
      Object.assign(definitions[0], {
        __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: JSON.stringify(true),
      });
      return definitions;
    });
  },
});
