/*
 * Copyright (C) 2017 - present Instructure, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */

package com.instructure.canvasapi2.models;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationPreferenceResponse extends CanvasComparable<NotificationPreferenceResponse> {

    @SerializedName("notification_preferences")
    public List<NotificationPreference> notificationPreferences = new ArrayList<>();

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public Date getComparisonDate() {
        return null;
    }

    @Override
    public String getComparisonString() {
        return null;
    }

    @Override
    public int compareTo(NotificationPreferenceResponse comparable) {
        return super.compareTo(comparable);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.notificationPreferences);
    }

    public NotificationPreferenceResponse() {
    }

    private NotificationPreferenceResponse(Parcel in) {
        in.readTypedList(this.notificationPreferences, NotificationPreference.CREATOR);
    }

    public static final Creator<NotificationPreferenceResponse> CREATOR = new Creator<NotificationPreferenceResponse>() {
        public NotificationPreferenceResponse createFromParcel(Parcel source) {
            return new NotificationPreferenceResponse(source);
        }

        public NotificationPreferenceResponse[] newArray(int size) {
            return new NotificationPreferenceResponse[size];
        }
    };
}
