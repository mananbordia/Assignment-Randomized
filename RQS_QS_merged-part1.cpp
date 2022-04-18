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
ll comps;

ll qpartition(vll& arr, ll l , ll r){
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

void quicksort(vll& arr, ll l , ll r){
    if(l >= r) return;
    ll p = qpartition(arr,l,r);
    quicksort(arr, l, p - 1);
    quicksort(arr, p+1, r);
}

// ### Randomized quick sort #####
ll rpartition(vll& arr, ll l , ll r){
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
    return rpartition(arr, l , r);
}

void randomised_quicksort(vll& arr, ll l, ll r){
    if(l >= r) return;
    ll rp = partition_randomised(arr, l , r);
    randomised_quicksort(arr, l, rp-1);
    randomised_quicksort(arr, rp + 1, r);
}


void solve(){
    vector<int> n_vals{100, 1000, 10000, 100000, 1000000};
    vector<int> iterations_vals{100, 100, 10, 1, 1};
    cout<<"################ Assignment 1 : Part 1 ###################"<<endl;
    for(int ii=0 ; ii<5; ii++){
        ll n = n_vals[ii];
        vll a(n);
        srand(time(0));
        
        double tot_time_1 = 0;
        double tot_time_2 = 0;
        ll number_of_iteration = iterations_vals[ii];

        ll tot_comps = 0;

        for(ll i=0 ; i<number_of_iteration ; i++){
            f(i,n-1){
                a[i] = rand()%n;
            }
            comps = 0;
            clock_t begin_time = clock();
            quicksort(a,0,n-1);
            tot_time_1 += double(clock() - begin_time);
            tot_comps += comps;

            begin_time = clock();
            quicksort(a,0,n-1);
            tot_time_2 += double(clock() - begin_time);
        }

        double avg_runtime_qsort = 1000.0*(tot_time_1/number_of_iteration)/CLOCKS_PER_SEC;
        double avg_comparision_qsort = ((double)tot_comps/(number_of_iteration));
        double avg_runtime_double_qsort = 1000.0*((tot_time_1+tot_time_2)/number_of_iteration);
        
    // ## Randomized Quick sort
        a = vll(n);
        srand(time(0));
        f(i,n-1){
            a[i] = rand()%n;
        }
    
        tot_time_1 = 0;
        tot_time_2 = 0;
        tot_comps = 0;

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
        cout<<"2nlog(n) : "<< 2*n*log2(n)<<endl;
        cout<<"Average running time of Quick Sort : " << avg_runtime_qsort <<endl;
        cout<<"Average running time of Randomized Quick Sort : " << 1000.0*(tot_time_1/number_of_iteration)/CLOCKS_PER_SEC<<endl;
        cout<<"Average no. of comparisons during Quick Sort : "<<avg_comparision_qsort<<endl;;
        cout<<"Average no. of comparisons during Randomized Quick Sort : "<<((double)tot_comps/(number_of_iteration))<<endl;
        cout<<"Average value of double sort time by Quick Sort : "<<avg_runtime_double_qsort/CLOCKS_PER_SEC<<endl;
        cout<<"Average value of double sort time by Randomized Quick Sort : "<<1000.0*((tot_time_1+tot_time_2)/number_of_iteration)/CLOCKS_PER_SEC<<endl;
        
        cout<<"_____________________________________________________________"<<endl;
        cout<<"___________________________________________________________"<<endl;
    
    }
}

int main(){
    ios_base::sync_with_stdio(0) ;
    cin.tie(0) ; cout.tie(0) ;
    cout.precision(15);

    solve();
    
    #ifndef ONLINE_JUDGE
        cout<<"\nTime Elapsed: "<<1.0*clock()/ CLOCKS_PER_SEC<<" Sec\n";
    #endif
    return 0;
  
}





