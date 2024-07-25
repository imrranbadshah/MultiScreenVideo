import Foundation

@objc public class MultiscreenActivity: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
