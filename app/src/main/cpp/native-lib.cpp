#include <jni.h>
#include <string>
#include "Keys.h"

const char *getCollegiateAPIKey() {
    return WEBSTER_COLLEGIATE_KEY;
}

const char *getLearnersAPIKey() {
    return WEBSTER_LEARNERS_KEY;
}

extern "C" JNIEXPORT jstring JNICALL
Java_me_pushkaranand_copymean_data_DataRepository__getCollegiateAPIKey(JNIEnv *env, jobject pthis) {
    return env->NewStringUTF(getCollegiateAPIKey());
}

extern "C" JNIEXPORT jstring JNICALL
Java_me_pushkaranand_copymean_data_DataRepository__getLearnersAPIKey(JNIEnv *env, jobject pthis) {
    return env->NewStringUTF(getLearnersAPIKey());
}