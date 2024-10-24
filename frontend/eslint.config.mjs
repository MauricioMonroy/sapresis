import globals from "globals";
import pluginJs from "@eslint/js";
import pluginReact from "eslint-plugin-react";

export default [
  {files: ["**/*.{js,mjs,cjs,jsx}"]},
  {files: ["**/*.js"], languageOptions: {sourceType: "module"}},
  {languageOptions: {
      globals: {
        ...globals.browser,  
        process: "readonly",
        describe: "readonly",
        it: "readonly",
        cy: "readonly",  
      }
    }
  },
  pluginJs.configs.recommended,
  pluginReact.configs.flat.recommended,
];

