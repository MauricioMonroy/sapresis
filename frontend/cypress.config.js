/* eslint-disable no-undef */
/* eslint-disable no-unused-vars */
const { defineConfig } = require("cypress");

module.exports = defineConfig({
  projectId: "qitowx",
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
      on("after:run", (results) => {
        console.log("Pruebas finalizadas", results);
      });
    },
  },
  screenshotsFolder: "cypress/screenshots",
  screenshotOnRunFailure: true,
  trashAssetsBeforeRuns: true,
  video: true,
  report: true,
});
