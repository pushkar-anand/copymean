#include <jni.h>
#include <string>
#include "Keys.h"

extern "C" JNIEXPORT jstring JNICALL
Java_me_pushkaranand_copymeanx_ui_MainActivity_appId(
        JNIEnv *env,
        jobject pThis
) {
    char APP_ID[] = OXFORD_APP_ID;
    return env->NewStringUTF(APP_ID);
}

const char *getAppID() {
    return OXFORD_APP_ID;
}

const char *getAppKey() {
    return OXFORD_APP_KEY;
}

