#!/usr/bin/env node

const { create } = require('../dist/lib');
const [, , projectPath = ''] = process.argv;
return create(process.cwd(), projectPath);
