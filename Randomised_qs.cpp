// It's true that I don't have brain //
//IaMaNanBord//

#include <bits/stdc++.h>
using namespace std;

//Macro Shorthands
#define F                       first
#define S                       second
#define f(i,n)                  for(ll i=0;i<=n;i++)
#define rf(i,n)                 for(ll i=n;i>=0;i--)
#define Cf(i,a,b)               for(ll i=a;i<=b;i++)
#define Crf(i,b,a)              for(ll i=b;i>=a;i--)
#define endl                    "\n"           
#define pb                      push_back
#define mp                      make_pair
#define z                       998244353  
#define every(it,x)             for(auto &it:x)
#define SET(it,x)               for(auto &it:x){cin>>it;}
#define ins                     insert
#define INF						((ll)1e18)
#define Test                    ll T; cin>>T; while(T--)
#define all(v)                  v.begin(),v.end()
#define nline                   cout<<endl
#define SZ(v)                   (ll)v.size()
#define pll                     pair<ll,ll>  
#define make(x,a)                memset(x, a, sizeof x)
 
//Debugging
#ifndef ONLINE_JUDGE
    #define revelio(args...)        { string _s = #args; replace(_s.begin(), _s.end(), ',', ' ');stringstream _ss(_s); istream_iterator<string> _it(_ss); err(_it, args); }
#else
    #define revelio(args...)        {}
#endif

void err(istream_iterator<string> it) {}
template<typename T, typename... Args>
void err(istream_iterator<string> it, T a, Args... args) {cerr << *it << " = " << a << endl; err(++it, args...);}

//Data types
typedef long long               ll;
typedef vector<ll>              vll;
typedef vector<string>          vstr;
typedef vector<char>            vchar;
typedef vector<pair<ll,ll> >    vpll;
typedef vector<vector<ll>>      vvll;
typedef set<ll>                 sll;
typedef set<string>             sstr;
typedef set<pair<ll,ll> >       spll;
typedef map<ll,ll>              mllll;
typedef map<string,ll>          mstrll;
typedef queue<ll>               qll;



//Functions
ll powMod(ll x,ll y)            {ll p=1; while(y){if(y%2){p=(p*(x%z))%z;} y/=2; x=((x%z)*(x%z))%z;} return p;}
ll CpowMod(ll x,ll y, ll w)     {ll p=1; while(y){if(y%2){p=(p*(x%w))%w;} y/=2; x=((x%w)*(x%w))%w;} return p;}
ll invMod(ll x)                 {return powMod(x,z-2);}
ll CinvMod(ll x,ll w)           {return CpowMod(x,w-2,w);}
ll gcd(ll a, ll b)              {return b == 0 ? a : gcd(b, a % b);}
// ll exGcd(ll a,ll b,ll &x, ll &y){if(b==0){y = 0;x = 1;return a;}ll xtmp, ytmp;ll g = exGcd(b,a%b,xtmp,ytmp);
//                                 x=ytmp; y = xtmp - (a/b)*ytmp;return g;}
// vll sub;
// void subSet(ll k,ll n)          {if(k==n+1){every(it,sub)cout<<it<<" ";cout<<endl;}
//                                 else{sub.pb(k);subSet(k+1,n);
//                                 sub.pop_back();subSet(k+1,n);}}
// vll perm; const ll N = (ll)2e6 ;bool isSelected[N];
// void permut(ll n)               {if(perm.size()==n){every(it,perm)cout<<it<<" ";cout<<endl;}
//                                 else{Cf(i,1,n){if(isSelected[i])continue;perm.pb(i);
//                                 isSelected[i]=1;permut(n);perm.pop_back();isSelected[i] =0;}}} 

// ll C[505][505], cSeen[505][505];
// ll nCr(ll n, ll r){
//     if(n < r) return 0;
//     if(r == 0) return 1;
//     if(C[n][r]) return C[n][r];
//     return C[n][r] = (nCr(n-1,r-1) + nCr(n-1, r))%z;
// }

ll comps;

ll partition(vll& arr, ll l , ll r){
    ll pivot = arr[r];
    ll i = l;
    for(int j=l ; j<r ; j++){
        if(arr[j] <= pivot){
            swap(arr[j], arr[i]);
            i++;
        }
    }
    comps += r - l - 1;
    swap(arr[i], arr[r]);
    return i;
} 

ll partition_randomised(vll& arr, ll l, ll r){
    ll rp = rand()%(r - l) + l;
    swap(arr[rp], arr[r]);
    return partition(arr, l , r);
}

void randomised_quicksort(vll& arr, ll l, ll r){
    if(l >= r) return;
    ll rp = partition_randomised(arr, l , r);
    // revelio(l, r,rp);
    randomised_quicksort(arr, l, rp-1);
    randomised_quicksort(arr, rp + 1, r);
}

void solve(){
    ll n;
    cin>>n;

    vll a(n);
    srand(time(0));

    f(i,n-1){
        a[i] = rand()%n;
    }
    
    double tot_time_1 = 0;
    double tot_time_2 = 0;
    ll number_of_iteration;
    cin>>number_of_iteration;
    ll tot_comps = 0;

    for(ll i=0 ; i<number_of_iteration ; i++){
        vll b = a;
        // Time start
        comps = 0;
        clock_t begin_time = clock();
        randomised_quicksort(b,0,n-1);
        tot_time_1 += double(clock() - begin_time);
        tot_comps += comps;

        begin_time = clock();
        randomised_quicksort(b,0,n-1);
        tot_time_2 += double(clock() - begin_time);
        // Time end
    }
    

    cout<<"n = "<<n<<endl;
    cout<<"Average running time of Randomized Quick Sort : " << (tot_time_1/number_of_iteration)/CLOCKS_PER_SEC<<endl;
    cout<<"Average no. of comparisons during Randomized Quick Sort : "<<((double)tot_comps/(number_of_iteration))<<endl;
    cout<<"Average value of double sort time by Randomized Quick Sort : "<<((tot_time_1+tot_time_2)/number_of_iteration)/CLOCKS_PER_SEC<<endl;
}

int main(){
    ios_base::sync_with_stdio(0) ;
    cin.tie(0) ; cout.tie(0) ;
    cout.precision(10);

    #ifndef ONLINE_JUDGE
        freopen("input.txt", "r", stdin);
        freopen("output.txt", "w", stdout);
	    freopen("error.txt","w",stderr);
    #endif

    solve();
    
    #ifndef ONLINE_JUDGE
        cout<<"\nTime Elapsed: "<<1.0*clock()/ CLOCKS_PER_SEC<<" Sec\n";
    #endif
    return 0;
  
}





