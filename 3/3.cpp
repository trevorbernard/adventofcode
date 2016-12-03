#include <iostream>
#include <string>

using namespace std;

int isValid(int a, int b,  int c) {
    return (a + b > c) && (b + c > a) && (a + c > b);
}

int main() {
    int a, b, c, d, e, f, g, h, i;
    int counter = 0;
    while(cin >> a >> b >> c
              >> d >> e >> f
              >> g >> h >> i) {
        if(isValid(a, d, g)) counter++;
        if(isValid(b, e, h)) counter++;
        if(isValid(c, f, i)) counter++;

        
    }
    cout << counter << endl;
    return 0;
}
