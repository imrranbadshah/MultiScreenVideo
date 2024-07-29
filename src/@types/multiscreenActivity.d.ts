declare module '@capacitor/core' {
    interface PluginRegistry {
        MultiscreenActivity: MultiscreenActivityPlugin;
    }
}

export interface MultiscreenActivityPlugin {
    openSplitScreen(options: { video1Url: string, video2Url: string }): Promise<void>;
}