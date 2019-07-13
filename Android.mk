LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_PACKAGE_NAME := popcornVPN

LOCAL_SDK_VERSION := current

LOCAL_SRC_FILES := $(call all-java-files-under, src)

# Include libraries

LOCAL_STATIC_JAVA_LIBRARIES := android-common

LOCAL_STATIC_JAVA_LIBRARIES += android-support-v4

LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-appcompat

LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res

LOCAL_ASSETS_DIR := $(LOCAL_PATH)/assets

LOCAL_AAPT_FLAGS := --auto-add-overlay

LOCAL_AAPT_FLAGS += --extra-packages android.support.v7.appcompat

LOCAL_CERTIFICATE := platform

include $(BUILD_PACKAGE)
