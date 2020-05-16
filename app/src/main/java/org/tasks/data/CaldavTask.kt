package org.tasks.data

import androidx.room.*
import com.todoroo.andlib.data.Property.*
import com.todoroo.andlib.data.Table
import com.todoroo.astrid.helper.UUIDHelper

@Entity(tableName = "caldav_tasks", indices = [Index(name = "cd_task", value = ["cd_task"])])
class CaldavTask {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cd_id")
    @Transient
    var id: Long = 0

    @ColumnInfo(name = "cd_task")
    @Transient
    var task: Long = 0

    @ColumnInfo(name = "cd_calendar")
    var calendar: String? = null

    @ColumnInfo(name = "cd_object")
    var `object`: String? = null

    @ColumnInfo(name = "cd_remote_id")
    var remoteId: String? = null

    @ColumnInfo(name = "cd_etag")
    var etag: String? = null

    @ColumnInfo(name = "cd_last_sync")
    var lastSync: Long = 0

    @ColumnInfo(name = "cd_deleted")
    var deleted: Long = 0

    @ColumnInfo(name = "cd_vtodo")
    var vtodo: String? = null

    @ColumnInfo(name = "cd_remote_parent")
    var remoteParent: String? = null

    @ColumnInfo(name = "cd_order")
    @Transient
    var order: Long? = null

    @ColumnInfo(name = "cd_remote_order")
    var remoteOrder: Long? = null

    @ColumnInfo(name = "cd_moved")
    @Transient
    var moved = false

    constructor()

    @Ignore
    constructor(task: Long, calendar: String?) {
        this.task = task
        this.calendar = calendar
        remoteId = UUIDHelper.newUUID()
        `object` = "$remoteId.ics"
    }

    @Ignore
    constructor(task: Long, calendar: String?, remoteId: String?, `object`: String?) {
        this.task = task
        this.calendar = calendar
        this.remoteId = remoteId
        this.`object` = `object`
    }

    fun isDeleted() = deleted > 0

    override fun toString(): String {
        return "CaldavTask(id=$id, task=$task, calendar=$calendar, `object`=$`object`, remoteId=$remoteId, etag=$etag, lastSync=$lastSync, deleted=$deleted, vtodo=$vtodo, remoteParent=$remoteParent, order=$order, remoteOrder=$remoteOrder, moved=$moved)"
    }

    companion object {
        const val KEY = "caldav"
        @JvmField val TABLE = Table("caldav_tasks")
        @JvmField val TASK = IntegerProperty(TABLE, "cd_task")
        @JvmField val DELETED = LongProperty(TABLE, "cd_deleted")
        @JvmField val CALENDAR = StringProperty(TABLE, "cd_calendar")
    }
}