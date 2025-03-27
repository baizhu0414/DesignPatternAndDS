package DesignPattern._06_AdapterPattern;

public class TranslateAdapter {
    public static void main(String[] args) {
        // 创建一个类适配器对象
        ClassAdapter adapter = new ClassAdapter();
        adapter.translate("中文", "英文", "你好世界！");
        adapter.translate("英语", "中文", "hello world！");

        // 创建一个类适配器对象
        ObjectAdapter adapterObj = new ObjectAdapter();
        adapterObj.translate("中文", "英文", "你好世界！");
        adapterObj.translate("英语", "中文", "hello world！");
    }
}

/**
 * 类型1. 类适配器：通过多重继承目标接口和被适配者类方式来实现适配
 */
class ClassAdapter extends Translator implements Target {

    /**
     * 实现Target需要的接口，使用已有Translator的能力。
     */
    @Override
    public void translate(String source, String target, String words) {
        if ("中文".equals(source) && "英文".equals(target)) {
            // 汉--》英
            this.translateInEn(words);
        } else {
            // 英--》汉
            this.translateInZh(words);
        }
    }
}

/**
 * 类型2. 对象适配器：使用组合的方式
 */
class ObjectAdapter implements Target {
 
    private Translator translator=new Translator();
 
    @Override
    public void translate(String source, String target, String words) {
        if("中文".equals(source) && "英文".equals(target)) {
            //汉--》英
            translator.translateInEn(words);
        } else {
            //英--》汉
            translator.translateInZh(words);
        }
    }
}


interface Target {

    /**
     * 翻译
     * 
     * @param source 母语
     * @param target 要翻译成的语种
     * @param words  内容
     */
    void translate(String source, String target, String words);
}

/**
 * 源对象(source）：充当翻译
 */
class Translator {

    // 英——》汉
    public void translateInZh(String words) {
        if ("hello world！".equals(words)) {
            System.out.println("翻译成中文：”你好世界！“");
        }
    }

    // 汉——》英
    public void translateInEn(String words) {
        if ("你好世界！".equals(words)) {
            System.out.println("Translate in English：”hello world！“");
        }
    }
}
