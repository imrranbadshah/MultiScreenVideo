export interface MultiscreenActivityPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  openSplitScreen(options: { video1Url: string, video2Url: string }): any;
}
