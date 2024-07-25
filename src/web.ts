import { WebPlugin } from '@capacitor/core';

import type { MultiscreenActivityPlugin } from './definitions';

export class MultiscreenActivityWeb extends WebPlugin implements MultiscreenActivityPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
