import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(MultiscreenActivityPlugin)
public class MultiscreenActivityPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "MultiscreenActivityPlugin"
    public let jsName = "MultiscreenActivity"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = MultiscreenActivity()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
}
