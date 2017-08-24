# LifeCycler
> current version : 1.0.1  

## Introduction :  
LifeCycler can help user to hook activity or fragment lifecycle event in anywhere of your codes.

## Compile

```
compile 'com.cxmax.lifecycler:library:1.0.1'
```

## Usage：
```
        // register in main thread
        LifeCycler.with(this)
                .addOnActivityCreatedListener(new Consumer() {
                    /**
                     * notice : this callback will run on UI thread
                     *
                     * @param activity
                     * @param bundle
                     */
                    @Override
                    public void run(Activity activity, Bundle bundle) {
                        //do something in activity onCreate lifecycle
                    }
                })
                .addOnActivityResumedListener(new Consumer() {
                    @Override
                    public void run(Activity activity, Bundle bundle) {
                        //do something in activity onResume lifecycle
                    }
                });
```

## Thanks :
1. [glide](https://github.com/bumptech/glide)
2. android source code
3. [Jween's LifeCycler](https://github.com/Jween) 

## License
   Copyright (C) 2017 cxMax  
  Copyright (C) 2017 LifeCycler

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
