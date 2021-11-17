import SwiftUI
import shared

func greet() -> String {
    return Greeting().greeting()
}

func getLocations() {
        let apiService = ApiService()
        apiService.about { (address) in
            print("🦋 Address received from API: \(address)")
        }
}

struct ContentView: View {
    var body: some View {
        Text(greet()).onAppear {
            print("isMainThread: \(Thread.isMainThread)")
            getLocations()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
