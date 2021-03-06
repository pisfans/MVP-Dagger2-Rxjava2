package com.zenglb.framework.dagger;

import com.zenglb.framework.demo.MemoryLeakTest;
import com.zenglb.framework.demo.demo.Rxjava_ZIP_Activity;
import com.zenglb.framework.demo.launch.LaunchActivity;
import com.zenglb.framework.mvp.handylife.AnyLifeActivity;
import com.zenglb.framework.mvp.handylife.AnyLifeFragment;
import com.zenglb.framework.navigation.MainActivityNaviModule;
import com.zenglb.framework.mvp.login.LoginActivity;
import com.zenglb.framework.navigation.MainActivityBottomNavi;
import com.zlb.dagger.component.BaseActivityComponent;
import com.zlb.dagger.module.DefaultActivityModule;
import com.zlb.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 全部放在这里来统一的管理 ！
 * 新建了一个Activity 的并且需要inject 的只需要添加两行代码
 * <p>
 * 大部分的页面都不需要再额外的提供对象的话只需要DefaultActivityModule 就好了，否则自定义XXActivityModule
 * <p>
 * <p>
 * 对个人而言，这样的好处在于：
 * 1.每次不再需要额外声明一个SubCompoent，再次减少模板代码
 * 2.每个Activity的Module都放在同一个AllActivitysModule中进行统一管理，每次修改只需要修改这一个类即可
 * 3.每个Activity所单独需要的依赖，依然由各自的Module进行管理和实例化，依然没有任何耦合
 */
@Module(subcomponents = {
        BaseActivityComponent.class  //1111111111 subcomponent=BaseActivityComponent
})
public abstract class AModuleAllActivityModule {

    /**
     * BaseActivity  <- BaseMVPActivity  这样的继承关系就好了
     *
     * 1 BaseActivity: 不要MVP模式，也不用DI 依赖注入（少写两行代码）就用这个吧
     *
     * 2 BaseMVPActivity 要MVP 要DI ，全局的依赖注入
     */

    //2222222 新建了一个Activity 的并且需要inject 的只需要添加两行代码 DefaultActivityModule 适用于只要全局Module 中的内容

    @ActivityScope
    @ContributesAndroidInjector(modules = DefaultActivityModule.class)
    abstract AnyLifeFragment contributeTabsFragmentInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = DefaultActivityModule.class)
    abstract LoginActivity contributeMainActivitytInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = DefaultActivityModule.class)
    abstract LaunchActivity contributeLaunchActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityNaviModule.class)
    abstract MainActivityBottomNavi contribute2Injector();

//    @ActivityScope
//    @ContributesAndroidInjector(modules = MVPActivityModule.class)
//    abstract TaskMVPActivity contribute3Injector();


    @ActivityScope
    @ContributesAndroidInjector(modules = DefaultActivityModule.class)
    abstract Rxjava_ZIP_Activity contribute4Injector();

    @ActivityScope
    @ContributesAndroidInjector(modules = DefaultActivityModule.class)
    abstract MemoryLeakTest contribute5Injector();

    @ActivityScope
    @ContributesAndroidInjector(modules = DefaultActivityModule.class)
    abstract AnyLifeActivity contributeTabsActivityInjector();


//    Pro-tip: If your subcomponent and its builder have no other methods or supertypes than the ones mentioned in step #2, you can use @ContributesAndroidInjector to generate them for you. Instead of steps 2 and 3, add an abstract module method that returns your activity, annotate it with @ContributesAndroidInjector, and specify the modules you want to install into the subcomponent. If the subcomponent needs scopes, apply the scope annotations to the method as well.

}
