文档原作者为哔哩哔哩`@Felis__Catus_`

本文档为第17期的辅助文档，主要对视频内容进行**补充**，*建议略过一边视频后观看*。

**注：文章中类似MOD ID或者类名如FCEntity，FCEntityRenderer，以及资源文件assets下的tutorial文件夹等等，需要根据自身使用情况做更改，并且在需要的地方要与自己的MOD ID匹配，否则会出错。**

本期主要内容是创建一个生物实体

在Minecraft里面，绝大多数的非方块对象是以实体展现的，例如各种生物，玩家，掉落的物品，矿车等等。

在某种极端条件下的正常游戏中我们都可以把它们视为同一种东西。

本期以创建一个生物为例，粗略地了解一下MC的Entity。

首先创建EntityTypeList

```java
public class EntityTypeList
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITIES, Tutorial.MOD_ID);
    public static final RegistryObject<EntityType<FCEntity>> FC_ENTITY = ENTITY_TYPE.register("fc_entity", ()-> EntityType.Builder.create(FCEntity::new, EntityClassification.MONSTER).size(1f,1f).build(new ResourceLocation(Tutorial.MOD_ID,"fc_entity").toString()));
}
```

这样的格式相信大家并不陌生了，DeferredRegister，老朋友了，和物品不同的是，这里运用到了泛型。

以第一个里面的`<EntityType>`为例，这里指的是我们想要任意的EntityType，而后面跟着的`<?>`则是证明我们想要往里传入任意实体，此处的问号表示为通配符，相对于`<T>`来说灵活了一点，我们就可以指向多个对象。

剩下的以后会讲，着急知道的可以自己去看一看有关Java泛型的相关教程和书籍。

这样的格式在之前已经出现过，就在第14期介绍TileEntity的时候我们已经初步了解了相关格式与作用。

其次创建上述代码中的FCEntity,因为我们并没有这个类。

在创建之前，我们需要明确我们需要创建什么类型的Entity。

如果你想单纯地让它活着，那么就需要**LivingEntity**,

如果你想让它是个动物，那么就需要**AnimalEntity**,

如果你想让它是个敌对生物，那么我们可以选择**MobEntity**注意这里的Mob**并不单纯地指生物**，我们可以让它变得"Hostile"，根据Minecraft Wiki上的说法：

> Hostile mobs are dangerous, aggressive mobs that always attack the player within their respective detection ranges.
>
> 敌对生物是危险的、侵略性的生物，它们总是在各自的检测范围内攻击玩家。

如果你想让它变得和狼一样可驯服，那么就需要**TameableEntity**。

*当然了，如果你想照模板扒的话，比如创建一个和僵尸差不多的生物，也可以去看看**ZombieEntity**，其他生物同理。*

但无论如何，他们都是属于一个庞大的体系——Entity.

如果用到一个关系描述的话，那便是以下情况：

TameableEntity∈AnimalEntity∈MobEntity∈LivingEntity∈Entity（以继承关系看）



然后在主类中添加进总线，像之前那样。

```java
EntityTypeList.ENTITY_TYPE.register(modEventBus);
```

我们本期以创建一个新的生物为例

FCEntity中的内容是这样的：

```java
public class FCEntity extends MobEntity {

    public FCEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20f)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1f)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 1.4f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 1.4f);
    }
}
```

抛开构建不讲，我们首先了解一下Attribute（属性），除去代表方块和物品的`Properties`，代表生成的`Feature`，我们开始了解代表生物的`Attribute`。

此处用到的方法在MobEntity中有，在128行左右，叫做func_233666_p_（这个名字还是蛮好记的）

源码为

```java
public static AttributeModifierMap.MutableAttribute func_233666_p_() {
   return LivingEntity.registerAttributes().createMutableAttribute(Attributes.FOLLOW_RANGE, 16.0D).createMutableAttribute(Attributes.ATTACK_KNOCKBACK);
}
```

所以我们可以照着他的写，就变成了上面我们写的东西。

那么具体讲一下Attribute

我们创建了5个Attribute，分别是`MAX_HEALTH`,`ATTACK_DAMAGE`,`ATTACK_KNOCKBACK`,`MOVMENT_SPEED`,`ATTACK_SPEED`

分别是生命值，攻击力，攻击击退，移动速度，攻击速度。

其中生命值是**必须添加**的，因为我们的生物必须要有一个生命值。

如果你不知道这些Attribute的具体值该设置为多少的话，这里有一份ZombieEntity的数值可以参考。

```java
.createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D)
.createMutableAttribute(Attributes.MOVEMENT_SPEED, (double)0.23F)
.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D)
.createMutableAttribute(Attributes.ARMOR, 2.0D)
.createMutableAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS);
```

我们来具体看一看其他的Attribute，括号第一个数值为默认值，后一个为数值范围（最小值-最大值）d为double，f为float

|        FOLLOW_RANGE(32, 0-2048d)         |              跟随距离              |
| :--------------------------------------: | :--------------------------------: |
|    **KNOCKBACK_RESISTANCE(0, 0-1d)**     |            **击退抗性**            |
| **FLYING_SPEED((double 0.4f), 0-1024d)** |            **飞行速度**            |
|           **ARMOR(0, 0-30d)**            |              **护甲**              |
|      **ARMOR_TOUGHNESS(0, 0-20d)**       | **护甲韧性（即生命值上方盔甲条）** |
|         **LUCK(0, -1024-1024d)**         |              **幸运**              |
| **ZOMBIE_SPAWN_REINFORCEMENTS(0, 0-1d)** |          **生成僵尸增援**          |
|  **HORSE_JUMP_STRENGTH(0.7d, 0-2.0d)**   |           **马跳跃力量**           |

这些基本上通过文字加上一点游戏经验都可以了解，但是这个僵尸增援我们需要了解一下（因为有很多人不了解）

以下内容来自Minecraft Wiki

> ### 增援
>
> 在所有难易度中，类似于
>
> 在困难难易度下，受到伤害的僵尸会生成一些额外的僵尸前来「增援」。每一只僵尸都有「呼叫增援能力」的属性，发动该属性的概率是0-10%。「领头」僵尸（0-5%，取决于 当僵尸被一个实体伤害，或在选定某个实体作为目标后受伤，僵尸最多会尝试随机选择生成增援僵尸的位置50次（三个方向轴上0或±7-40米外），生成位置需要是固体方块的上表面，亮度等级小于等于9，7米内没有玩家，且没有任何可碰撞的实体和方块阻碍增援的生成。呼叫增援的僵尸和前来增援的僵尸都会在「呼叫增援能力」属性中给予5%的惩罚，降低无限生成增援僵尸的概率。
>
> 这些效果可以透过以尽量少的攻击次数杀死僵尸、使用环境伤害（例如仙人掌或熔岩）或完全忽略它们而避免。此外，即便在困难难易度下，增援僵尸不会在`/gamerule mobGriefing``false`

所以最小值为0（不召唤），最大值为1（一定召唤）

并且偏向于命令侧的玩家对此可能并不陌生，在游戏中我们也可以通过指令

`/summon Zombie ~ ~ ~ {Attributes:[{Name:generic.maxHealth,Base:20},{Name:generic.knockbackResistance,Base:1},{Name:generic.movementSpeed,Base:1},{Name:generic.attackDamage,Base:3}]}`

来生成一个20生命，1击退抗性，1移动速度，3攻击伤害的僵尸



但是我们并没有使用重写方法，而且方法为static，所以我们需要在CommonSetup事件中call它。

在主类Tutorial中的setup方法中填入以下语句

```
DeferredWorkQueue.runLater(() -> {
    GlobalEntityTypeAttributes.put(EntityTypeList.FC_ENTITY.get(), FCEntity.setAttributes().create());
});
```

在这之后，我们会涉及到一些浅显的生物AI的调整

基本上需要net.minecraft.entity.MobEntity中的一些重写方法，相应的，根据你继承的类不同，你需要找到对应的类查看，这里我们继承的是MobEntity，因此我们需要在MobEntity中去找。

所以我们在FCEntity中加入以下重写

```java
@Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6f));
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }
	
@Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 10;
    }
```

其中的`registerGoals`代表我们注册了一个**目标**

其中用到了一个`addGoal()`的方法，我们看一下它的定义，在`net.minecraft.entity.ai.goal.GoalSelector`中

```java
public void addGoal(int priority, Goal task) {
   this.goals.add(new PrioritizedGoal(priority, task));
}
```

那么我们可以看到这类实体动作有一个名称叫做`task`，同样我们在浏览其他相关代码的时候也会出现，所以要知道task是什么。

这个方法调用了`PrioritizedGoal()`，代表我们的task要有一个优先级，这与我们之前的index不同，优先级是区分大小的，越大的数字代表越优先，当有几个task同时满足条件的时候，优先进行优先级高的task。

在上面的代码里面我们添加了一个优先值为1的`LookRandomlyGoal()`，这个方法主要是让生物随机地四处看

`LookAtGoal()`是让生物看向制定目标，括号后的值意思是this（这个生物，指FCEntity）在6格内会看向Player（玩家）

`NearestAttackableTargetGoal<>()`会将最近的对象视为优先攻击目标，这里我们传入的是玩家实体类

**如果你想要的制作一个敌对性生物，可以参考ZombieEntity中的Goal。**

`getExperiencePoints()`就不用多说了，就是生物死后的经验值

此外，还有一些重写方法可能会用的上，以下列举了MobEntity中的大部分，根据实际情况自选

|         getAttackTarget         |                   获取Task中用于追踪的目标                   |
| :-----------------------------: | :----------------------------------------------------------: |
|         setAttackTarget         |               设置一个活动的目标用于Task中追踪               |
|         getTalkInterval         |      获取说话间隔（单位tick），间隔时间内生物会沉默不语      |
|        playAmbientSound         |                     在生物位置上播放声音                     |
|            baseTick             |                  每个tick中都被主实体类调用                  |
|          playHurtSound          |                         播放受伤音效                         |
|     spawnExplosionParticle      |                     实体周围产生爆炸粒子                     |
|              tick               |                被调用用来更新实体的位置和逻辑                |
|     updateMovementGoalFlags     |     在被控制或骑乘时被动决定移动，跳，看（取决于控制者）     |
|         writeAdditional         |    用于一些NBT的更改，比如捡东西和带盔甲（具体可看源码）     |
|         setAIMoveSpeed          |                     设置用于AI的移动速度                     |
|           livingTick            | 频繁的调用，用来让生物在每个tick中做出反应（比如僵尸在阳光下燃烧） |
|     updateEquipmentIfNeeded     | 检测是否应该捡起武器或盔甲，如果有已有装备则自动替换更好的装备 |
|          checkDespawn           |                     达到条件，让实体消失                     |
|           faceEntity            |       更改角度，让调用方法的实体面向作为参数传入的实体       |
|         updateRotation          |     更新旋转，三个参数分别为当前旋转、预期旋转、最大增量     |
|           canSpawnOn            | 如果实体是从刷怪箱生成的，或实体在给定的BlockPos上生成，返回true |
|      getMaxSpawnedInChunk       |         设置在一个区块内一次性最多生成多少个目标实体         |
|        getMaxFallHeight         |           实体被允许跳跃的最大高度（用于寻路追踪）           |
|  setEquipmentBasedOnDifficulty  |                 根据难度为实体提供对应的装备                 |
| setEnchantmentBasedOnDifficulty |                   根据难度为实体的装备附魔                   |
|          canBeSteered           | 如果满足指导实体的条件，返回true（比如骑着猪拿着胡萝卜鱼杆） |
|        enablePersistence        |                         生物持续存在                         |
|       isNoDespawnRequired       |        返回persistenceRequired，是否允许实体自然消失         |
|       updateLeashedState        |            应用栓绳的逻辑，比如拖动实体或弄断栓绳            |
|          clearLeashed           |                       清除实体上的缰绳                       |
|         setLeashHolder          |                       设置要拴住的实体                       |
|          isServerWorld          |                    返回实体是否在服务器里                    |
|             setNoAI             |                      设置是否禁用实体AI                      |
|          isAIDisabled           |                      获取实体AI是否禁用                      |
|            setChild             |                   设置小实体（比如小僵尸）                   |

此外，如果想要设定实体死亡触发，需要用到LivingEntity类中的`onDeath()`重写，具体可以自己查找。

让生物死亡播放声音也可以用到LivingEntity中的`getDeathSound()`

强烈建议把以上介绍的Entity类都看一遍，因为不同的功能可能会在不同的类中出现。



那么我们的生物差不多就设置完成了，接下来我们需要将生物模型弄出来。

我们创建一个Entity的包（正规写法中包名是要小写的，但是这里一错到底了），在里面新建一个FCEntityRenderer类，再在Entity包中新建一个Model包，在里面创建一个FCEntityModel类。

接下来我们打开Blockbench（或其他类似软件），但是Blockbench更简单，这里推荐用这个。

我又花了2分钟创建了一个模型（好像一个烤面包机233）

创建模型的时候别忘记选择New->Modded Entity

![](C:\Users\FelisCatus\Pictures\blockbench.png)





把材质弄好后，我们把材质保存，然后把模型导出，选择Export-> Export Java Entity，然后我们会得到一个java文件。

我们把得到的java文件中的内容转到FCEntityModel中，然后改一下名字，让类名和文件中的类名一致。

为了传入一会要用到的MobRenderer以及方便长期的应用，我们可以利用泛型，将我们的文件变得更灵活，这个`<T>`的作用就是告诉编译器这个地方我先留着，到时候用到了我再告诉你这是个什么类型，还是推荐大家去找一找关于泛型的资料。

```java
public class FCEntityModel<T extends FCEntity> extends EntityModel<T>
{
	...
}
```

所以我们可以用到任何继承FCEntity的FCEntityModel继承任何



接下来就到把模型渲染出来的步骤了

进入FCEntityRenderer类，使它继承MobRenderer，也需要用到泛型

在源代码中，MobRenderer是这样定义的

```java
public abstract class MobRenderer<T extends MobEntity, M extends EntityModel<T>> extends LivingRenderer<T, M>
```

其中M与T作用是差不多的，所以我们的FCEntityModel和这个简直门当户对，完全不需要任何处理。

```java
public class FCEntityRenderer extends MobRenderer<FCEntity, FCEntityModel<FCEntity>>
{
    public static final ResourceLocation ENTITY_LOCATION = new ResourceLocation(Tutorial.MOD_ID, "textures/entity/fc_entity.png")
    
	public FCEntityRenderer(EntityRendererManager manager)
    {
        super(manager, new FCEntityModel<>(),1.0f)
    }
    
    @Override
    public ResourceLocation getEntityTexture(FCEntity entity)
    {
        return ENTITY_TEXTURE;
    }
}
```

同样的我们需要注册这个材质，并且把它用到实体上

这一步需要FMLClientSetupEvent，我们回到主类，找到clientSetup方法，填上：

`RenderingRegistry.registerEntityRenderingHandler(EntityTypeList.FC_ENTITY.get(),FCEntityRenderer::new);`

然后我们在资源文件夹中的resources>assets>tutorial>textures中新建文件夹叫做entity，把我们之前导出的材质改名为fc_entity放在里面。

然后可以打开游戏看看效果了，使用指令/summon tutorial:fc_entity便会得到一个人畜无害的实体。