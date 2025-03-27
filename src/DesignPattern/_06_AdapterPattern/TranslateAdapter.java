package DesignPattern._06_AdapterPattern;

public class TranslateAdapter {
    public static void main(String[] args) {
        // 创建一个类适配器对象
        ClassAdapter adapter = new ClassAdapter();
        adapter.translate("中文", "英文", "你好世界！");
        adapter.translate("英语", "中文", "hello world！");
    }
}

/**
 * @author Created by njy on 2023/6/11
 *         类适配器：通过多重继承目标接口和被适配者类方式来实现适配
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
 * @author Created by njy on 2023/6/8
 *         源对象(source）：充当翻译
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
