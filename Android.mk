LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := Orbot
LOCAL_MODULE_TAGS := optional
LOCAL_PACKAGE_NAME := Orbot

orbot_root  := $(LOCAL_PATH)
orbot_dir   := app
orbot_out   := $(PWD)/$(OUT_DIR)/target/common/obj/APPS/$(LOCAL_MODULE)_intermediates
orbot_build := $(orbot_root)/$(orbot_dir)/build
orbot_apk   := build/outputs/apk/full/release/$(orbot_dir)-full-release-unsigned.apk

$(orbot_root)/$(orbot_dir)/$(orbot_apk):
	rm -Rf $(orbot_build)
	mkdir -p $(orbot_out)
	ln -sf $(orbot_out) $(orbot_build)
	cd $(orbot_root)/$(orbot_dir) && gradle assembleRelease

LOCAL_CERTIFICATE := platform
LOCAL_SRC_FILES := $(orbot_dir)/$(orbot_apk)
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)

include $(BUILD_PREBUILT)
