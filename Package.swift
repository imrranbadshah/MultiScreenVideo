// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "OpenMultiscreenActivity",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "OpenMultiscreenActivity",
            targets: ["MultiscreenActivityPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "MultiscreenActivityPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/MultiscreenActivityPlugin"),
        .testTarget(
            name: "MultiscreenActivityPluginTests",
            dependencies: ["MultiscreenActivityPlugin"],
            path: "ios/Tests/MultiscreenActivityPluginTests")
    ]
)