export interface MultiscreenActivityPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
