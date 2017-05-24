package com.mybox.activity

import android.os.SystemClock
import android.util.Log

import java.io.Serializable

import rx.Observable
import rx.Observer
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.functions.Action1
import rx.functions.Func1
import rx.schedulers.Schedulers

class RxTavaTest {

    internal var tag = "RxjavaTest"

    inner class UserInfo {
        var id: Int = 0
        var name: String? = null
        var points: Int = 0
        var hobbies: Array<String>? = null
    }

    //模拟从网络请求
    private fun getUserInfoFromNet(id: Int): UserInfo {
        val userInfo = UserInfo()
        userInfo.points = 100
        return userInfo
    }

    //模拟从数据获取信息
    private fun getNameFromDb(id: Int): String {
        return "username"
    }

    //模拟从数据获取信息
    private fun getHobbies(id: Int): Array<String> {
        return arrayOf("singing", "running", "shopping")
    }

    private var subscription: Subscription? = null

    /**
     * Observable是被观察者，通过create创建（还有其他方式，后面将会讲到），传入一个OnSubscribe对象，
     * 当Observable调用subscribe进行注册观察者时，OnSubscribe的call方法会触发。
     */
    fun test01() {
        Log.e(tag, "------------test01-----------" + "\n----------简单实例01---------")

        //创建被观察者
        val observable = Observable.create(Observable.OnSubscribe<String> { subscriber ->
            //调用观察者的回调
            subscriber.onNext("我是")
            subscriber.onNext("RxJava")
            subscriber.onNext("简单示例")
            subscriber.onError(Throwable("出错了"))
            subscriber.onCompleted()
        })

        //创建观察者
        val observer = object : Observer<String> {
            override fun onCompleted() {
                Log.e(tag, "onCompleted")
            }

            override fun onError(e: Throwable) {
                Log.e(tag, e.message)
            }

            override fun onNext(s: String) {
                Log.e(tag, s)
            }
        }

        //注册，是的观察者和被观察者关联，将会触发OnSubscribe.call方法
        observable.subscribe(observer)


        val observable1 = Observable.create(Observable.OnSubscribe<Int> { subscriber ->
            subscriber.onStart()
            subscriber.onNext(2222)
            subscriber.onNext(22222)
            subscriber.onCompleted()
            subscriber.onError(Throwable("22222222222222222222"))
        })
        val observer1 = object : Observer<Int> {
            override fun onCompleted() {
                Log.e(tag, "onCompleted222222222")
            }

            override fun onError(e: Throwable) {
                Log.e(tag, e.message)
            }

            override fun onNext(integer: Int?) {
                Log.e(tag, integer!!.toString() + "")
            }
        }

        observable1.subscribe(observer1)
    }

    //    运行结果：
    //
    //            =====>: ------------test01-----------
    //            ----------简单实例01---------
    //            =====>: 我是
    //            =====>: RxJava
    //            =====>: 简单示例
    //            =====>: 出错了

    //    Observable是被观察者，通过create创建（还有其他方式，后面将会讲到），传入一个OnSubscribe对象，
    //    当Observable调用subscribe进行注册观察者时，OnSubscribe的call方法会触发。Observer是观察者，
    //    他有三个回调方法：
    //    onNext      ：接受到一个事件
    //    onCompleted ：接受完事件后调用，只会调用一次
    //    onError     ：发生错误时调用，并停止接受事件，调用一次
    //    onCompleted和onError不会同时调用，只会调用其中之一

    /**
     * 观察者除了使用Observer，还可以使用Subscriber，它跟 Observer作用一样，如下：
     */
    fun test02() {
        Log.e(tag, "------------test02-----------" + "\n----------简单实例02---------")

        //创建被观察者
        val observable = Observable.create(Observable.OnSubscribe<String> { subscriber ->
            //调用观察者的回调
            subscriber.onNext("我是")
            subscriber.onNext("RxJava")
            subscriber.onNext("简单示例")
            subscriber.onCompleted()
            subscriber.onError(Throwable("出错了"))
        })

        //创建观察者
        val subscriber = object : Subscriber<String>() {

            override fun onStart() {
                Log.e(tag, "onStart")
            }

            override fun onCompleted() {
                Log.e(tag, "onCompleted")
            }

            override fun onError(e: Throwable) {
                Log.e(tag, e.message)
            }

            override fun onNext(s: String) {
                Log.e(tag, s)
            }
        }
        //注册，使得观察者和被观察者关联，将会触发OnSubscribe.call方法
        observable.subscribe(subscriber)
    }

    //    运行结果
    //
    //              =====>: ------------test02-----------
    //              ----------简单实例02---------
    //              =====>: onStart
    //              =====>: 我是
    //              =====>: RxJava
    //              =====>: 简单示例
    //              =====>: onCompleted
    //    这里只是多了一个onStart方法 ，这个方法会在Observable调用方法subscribe注册观察者时调用一次，表明事件要开始了。
    //    因为Subscriber只是对Observer的扩展，用法都一样，所以后面例子都用 Subscriber做观察者。

    /* 观察者Subscriber和Observer都需要写三个回调，有时候我们只关系其中一个回调，或者两个*/

    /**
     * from方法将传入的数组或 Iterable 拆分成具体对象后，依次发送出来。
     */
    fun test03() {
        Log.e(tag, "------------test03-----------" + "\n----------创建观察者01---------")

        val ints = arrayOf(1, 2, 3, 4)

        Observable.from(ints).subscribe(object : Subscriber<Int>() {
            override fun onCompleted() {
                Log.e(tag, "onCompleted")
            }

            override fun onError(e: Throwable) {
                Log.e(tag, e.message)
            }

            override fun onNext(integer: Int?) {
                Log.e(tag, integer!!.toString() + "")
            }
        })
    }
    //    运行结果
    //
    //            =====>: ------------test03-----------
    //            ----------创建观察者01---------
    //            =====>: 1
    //            =====>: 2
    //            =====>: 3
    //            =====>: 4
    //            =====>: onCompleted
    //    from方法将传入的数组或 Iterable 拆分成具体对象后，依次发送出来。
    //

    /**
     * 和from方法一样，just(T…): 将传入的参数依次发送出来。
     */
    fun test04() {
        Log.e(tag, "------------test04---------------------------------------" + "\n----------创建被观察者02---------")
        Observable.just(1, 2, 3, 4).subscribe(object : Subscriber<Int>() {
            override fun onCompleted() {
                Log.e(tag, "onCompleted")
            }

            override fun onError(e: Throwable) {
                Log.e(tag, e.message)
            }

            override fun onNext(integer: Int?) {
                Log.e(tag, integer!!.toString() + "")
            }
        })


        Observable.just("2", "2222", "6666", 555, ArrayIndexOutOfBoundsException(), getHobbies(0)).subscribe(object : Subscriber<Serializable>() {
            override fun onCompleted() {

            }

            override fun onError(e: Throwable) {

            }

            override fun onNext(serializable: Serializable) {

                Log.e(tag, serializable.toString() + "  serializable--------------------------")
                if (serializable is Int) {
                    Log.e(tag, serializable.toString() + "  Integer")
                } else if (serializable is String) {
                    Log.e(tag, serializable + "  String")

                }
            }
        })
    }
    //    运行结果

    //            =====>: ------------test04-----------
    //            ----------创建被观察者02---------
    //            =====>: 1
    //            =====>: 2
    //            =====>: 3
    //            =====>: 4
    //            =====>: onCompleted


    /**
     * 这里使用了Action1和Action0, Action0 是 RxJava 的一个接口，它只有一个方法 call()，这个方法是无参无返回值的；
     * 由于 onCompleted() 方法也是无参无返回值的，因此 Action0 可以被当成一个包装对象，
     * 将 onCompleted() 的内容打包起来将自己作为一个参数传入 subscribe() 以实现不完整定义的回调。
     * Action1 也是一个接口，它同样只有一个方法 call(T param)，这个方法也无返回值，但有一个参数；
     * 与 Action0 同理，由于 onNext(T obj) 和 onError(Throwable error) 也是单参数无返回值的，
     * 因此 Action1 可以将 onNext(obj) 和 onError(error) 打包起来传入 subscribe() 以实现不完整定义的回调。
     * 事实上，虽然 Action0 和 Action1 在 API 中使用最广泛，
     * 但 RxJava 是提供了多个 ActionX 形式的接口 (例如 Action2, Action3) 的，
     * 它们可以被用以包装不同的无返回值的方法。
     */
    fun test05() {
        Log.e(tag, "------------test05-----------" + "\n----------创建观察者其他形式---------")

        val strs = arrayOf("aa", "bb", "cc")
        val onNextAction = Action1<String> { s -> // onNext()
            Log.d(tag, s)
        }
        val onErrorAction = Action1<Throwable> { throwable -> // onError()
            Log.e(tag, throwable.message)
        }
//        val onCompletedAction = Action0 // onCompleted()
//        {
//            Log.d(tag, "completed")
//        }
//
//        val observable = Observable.from(strs)
//        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
//        observable.subscribe(onNextAction)
//        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
//        observable.subscribe(onNextAction, onErrorAction)
//        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
//        observable.subscribe(onNextAction, onErrorAction, onCompletedAction)

    }

    /**
     * 5、线程调度
     * RxJava遵循线程不变原则，在不做特殊处理的情况下，在哪个线程调用 subscribe()，就在哪个线程生产事件；
     * 在哪个线程生产事件，就在哪个线程消费事件。如果需要切换线程，就需要用到 Scheduler （调度器）
     */
    fun test06() {
        Log.e(tag, "------------test06-----------" + "\n----------线程调度---------")
        //从网络上根据用户id，请求对应用户，并显示用户积分到界面
        val s = Observable.OnSubscribe<Int> { subscriber ->
            val id = 111
            val userinfo = getUserInfoFromNet(id)
            subscriber.onNext(userinfo.points)
        }


        val observable = Observable
                .create(s)
                .subscribeOn(Schedulers.io())//事件产生在io线程
                .observeOn(AndroidSchedulers.mainThread())


        observable.subscribe { points -> Log.e(tag, "显示用户积分：" + points!!) }
    }

    //    运行结果
    //
    //            =====>: ------------test06-----------
    //            ----------线程调度---------
    //            =====>: 显示用户积分：100

    //    在io线程中根据id请求用户信息，在主线程中将用户积分显示到界面
    //    Schedulers.newThread(): 总是启用新线程，并在新线程执行操作,适用于复杂计算。
    //    Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）
    //    AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行
    //
    //    subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
    //    observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程

    /**
     * 6、变换
     * 举一个例子：通过用户id，从数据库中请求用户名称，只需要名称就行，id是int类型，用户名称是string类型
     * 这里的map有转换的作用，这里是吧Observable<Integer>转换成了Observable<String>,事件只能是一对一的转换，
     * 把发射id的事件，转换成了发射username的事件
    </String></Integer> */
    fun test07() {
        Log.e(tag, "------------test07-----------" + "\n----------变换01---------")
        //从数据库中根据id获取用户名称
        Observable.just(111)
                .map { id -> getNameFromDb(id!!) }
                .subscribe { s -> Log.e(tag, s) }
    }

    //    运行结果
    //
    //            =====>: ------------test07-----------
    //            ----------变换01---------
    //            =====>: username
    //    这里的map有转换的作用，这里是吧Observable<Integer>转换成了Observable<String>,事件只能是一对一的转换，把发射id的事件，转换成了发射username的事件

    /**
     * flatmap转换，举一个例子：根据id从数据库中获取指定用户的爱好，并打印出来
     *
     *
     * 这里将Observable<Integer>转换成了 Observable.from(getHobbies(id))，
     * 将发射id的事件，转换成了多个发射爱好的事件（爱好有多个），事件是一对多
    </Integer> */

    fun test08() {
        Log.e(tag, "------------test08-----------" + "\n----------变换02---------")
        Observable.just(111)
                .flatMap { id -> Observable.from(getHobbies(id!!)) }
                .subscribe { s -> Log.e(tag, s) }

    }

    //    运行结果
    //
    //            =====>: ------------test08-----------
    //            ----------变换02---------
    //            =====>: singing
    //            =====>: running
    //            =====>: shopping
    //    这里将Observable<Integer>转换成了 Observable.from(getHobbies(id))，将发射id的事件，转换成了多个发射爱好的事件（爱好有多个），事件是一对多

    /**
     * 7、反注册
     *
     *
     * rxJava是基于观察者模式的，我们知道观察者模式中有注册和反注册，反注册就是为了释放资源，防止内存泄露，rxjava中也是，我们也需要及时反注册并释放资源，
     *
     *
     * Subscription是注册关系，Observable调用subscribe方法时，会形成一个注册关系，
     * Subscription的isUnsubscribed()方法来判断是否已经反注册，unsubscribe()方法来进行反注册。
     * 一般情况下，当事件发送完成后，观察者会自动反注册，不用我们调用Subscription的unsubscribe()方法进行反注册，
     * 但是当有耗时操作时，我们就有必要进行反注册，上面代码的运行结果如下：
     */
    fun test09() {
        Log.e(tag, "------------test09-----------" + "\n----------反注册---------")
        subscription = Observable.create(Observable.OnSubscribe<String> { subscriber ->
            SystemClock.sleep(5000)//暂停5秒钟
            subscriber.onNext("hahaha")
            subscriber.onCompleted()//标志事件发送完毕，just和from会自动调用onCompleted
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { s -> Log.e(tag, s) }
    }
    //    /**
    //     * 运行结果
    //     * <p>
    //     * 在事件还没有发送完毕时（5秒之内），我们关闭了activity：
    //     * =====>: ------------test09-----------
    //     * ----------反注册---------
    //     * =====>: 是否已经反注册：false
    //     * =====>: 进行反注册
    //     * =====>: 是否已经反注册：true
    //     * 事件已经发送完（5秒之后）,我们关闭activity：
    //     * =====>: ------------test09-----------
    //     * ----------反注册---------
    //     * =====>: hahaha
    //     * =====>: 是否已经反注册：true
    //     */
    //    @Override
    //    protected void onDestroy() {
    //        super.onDestroy();
    //        Log.e(tag, "是否已经反注册：" + subscription.isUnsubscribed() + "");
    //        //先判断是否已经反注册
    //        if (!subscription.isUnsubscribed()) {
    //            Log.e(tag, "进行反注册");
    //            subscription.unsubscribe();
    //            Log.e(tag, "是否已经反注册：" + subscription.isUnsubscribed() + "");
    //        }
    //    }


    /**
     * 8、操作符使用
     *
     *
     * rxJava有着丰富的操作符，来对数据进行流式的操作处理，上面讲到的map和flatmap就是rxJava的操作符，由于操作符太多，只示范常用的几个：
     */
    fun test10() {
        Log.e(tag, "------------test10-----------" + "\n----------操作符---------")
        val strs = arrayOf("aa", "bb", "bb", "cc", "dd", "ee")
        val observable = Observable.from(strs)
        //filter(Func1)方法来过滤我们观测序列中不想要的值
        //take(count)方法来限制获取多少个数据
        Log.e(tag, "---------filter & take----------")
        observable
                .filter { s ->
                    //把已b结尾的数据去掉
                    !s.endsWith("b")
                }
                .take(3)//取3个数据
                .subscribe { s -> Log.e(tag, s) }
        Log.e(tag, "---------skip & first----------")
        observable
                .skip(3)
                .first()
                .subscribe { s -> Log.e(tag, s) }
    }
    //    运行结果
    //
    //            =====>: ------------test10-----------
    //            ----------操作符---------
    //            =====>: ---------filter & take----------
    //            =====>: aa
    //            =====>: cc
    //            =====>: dd
    //            =====>: ---------skip & first----------
    //            =====>: cc

    //    类似的这种简单的操作符还有：
    //    last() 取最后一个
    //    distinct() 去除重复的
    //    skipLast() 去除最后一个
    //    takeLast(count) 取最后count个
    //    limit(count) 限制个数
    //    doOnNext(Action1) 允许我们在每次输出一个元素之前做一些额外的事情，比如保存起来。
}
