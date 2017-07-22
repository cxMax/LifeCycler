# LifeCycler
> current version : 1.0  
```
compile 'com.cxmax.lifecycler:library:1.0'
```

## 介绍 :  
在任何一个类,只需要你传入当前的context(activity/fragment),就能通过LifeCycler去监听对应context的生命周期,然后具体在特定的生命周期做一些处理(类似onDestroy的时候释放资源).

## 为什么要使用LifeCycler :
<b>设想一个场景</b> :  
一个列表页面其中一个item的一个自定义View需要在activity onDestroy()的时候释放资源 ,   
在代码里面的体现是activity/fragment -> adapter -> viewholder -> viewholder's operational class.  
<b>解决方案</b> :  
常规思路是,制造一个一个callback向下传递,并在最终的operational class做对应的释放处理操作.  
但如果是使用LifeCycler的话,只需要在具体的操作类注册并监听onDestroy()在执行具体操作即可.

## 用法：
```
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

## 原理 :
主要是通过Application.ActivityLifecycleCallbacks来实现注册分发activity生命周期事件.   
具体原理可以通过以下两点来掌握 :   
1. glide的生命周期管理 ;   
http://www.jianshu.com/p/ab126050406c  
http://www.jianshu.com/p/317b2d6bde1b  
2. activity与fragment生命周期管理;   
http://blog.csdn.net/yinzhong39/article/details/45849847  
http://www.jianshu.com/p/1b3f829810a1


> 如果你没空看以上博客的话,可以直接看结论.   
1.fragment的生命周期是通过activity的生命周期函数(例如:onCreate(),onPause())进行分发的   
2.想要触发具体的activity的生命周期函数事件, 需要在application注册Application.ActivityLifecycleCallbacks,  
Application.ActivityLifecycleCallbacks会通过回调函数去分发已注册activity的生命周期事件

## 感谢 :
1. [glide](https://github.com/bumptech/glide)
2. android open source project
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
