import { registerPlugin } from '@capacitor/core';

import type { MultiscreenActivityPlugin } from './definitions';

const MultiscreenActivity = registerPlugin<MultiscreenActivityPlugin>('MultiscreenActivity', {
  web: () => import('./web').then(m => new m.MultiscreenActivityWeb()),
});

export * from './definitions';
export { MultiscreenActivity };
